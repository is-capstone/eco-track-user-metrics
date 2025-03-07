package com.enzulode.metrics.crud.file.processor;

import com.enzulode.metrics.crud.dao.entity.MetricsOnMetricsValues;
import com.enzulode.metrics.crud.dao.entity.MetricsValue;
import com.enzulode.metrics.crud.dao.repository.MetricsOnMetricsValuesRepository;
import com.enzulode.metrics.crud.dao.repository.MetricsRepository;
import com.enzulode.metrics.crud.dao.repository.MetricsValueRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Iterators;
import com.opencsv.CSVParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;

@Component
@Slf4j
@RequiredArgsConstructor
public class FileProcessorImpl implements FileProcessor {

  private static final CSVParser csvParser = new CSVParser();

  private final ObjectMapper objectMapper;
  private final MetricsValueRepository metricsValueRepository;
  private final MetricsRepository metricsRepository;
  private final MetricsOnMetricsValuesRepository metricsOnMetricsValuesRepo;
  private final PlatformTransactionManager tm;

  @Override
  public void processCsv(Long metricsId, InputStream is) {
    int batchSize = 100000; // 10
    var td = new DefaultTransactionDefinition();
    var ts = tm.getTransaction(td);
    try(var br = new BufferedReader(new InputStreamReader(is))) {
      var metrics = metricsRepository.findById(metricsId).orElseThrow(); // TODO: custom exception
      var linesIterator = br.lines()
          .parallel()
          .map(this::processCsvLine)
          .iterator();
      var valuesIterator = Iterators.paddedPartition(linesIterator, batchSize);

      List<MetricsValue> v;
      while(valuesIterator.hasNext()) {
        v = valuesIterator.next();
        v = v.stream().filter(Objects::nonNull).toList();
        v = metricsValueRepository.saveAll(v);

        var metricsOnValues = v.stream()
            .parallel()
            .map( el -> new MetricsOnMetricsValues(metrics, el) )
            .toList();
        metricsOnMetricsValuesRepo.saveAll(metricsOnValues);
      }
      tm.commit(ts);
    } catch (Exception e) {
      tm.rollback(ts);
      throw new RuntimeException("failed to process csv", e); // TODO: custom exception?
    }
  }

  private MetricsValue processCsvLine(String line) {
    try {
      return MetricsValue.fromCsv(csvParser.parseLine(line));
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse the line", e); // TODO: failure during csv line parsing
    }
  }

  @Override
  public void processJson(Long metricsId, InputStream is) {
    int batchSize = 10;
    var td = new DefaultTransactionDefinition();
    var ts = tm.getTransaction(td);
    try(var br = new BufferedReader(new InputStreamReader(is))) {
      var metrics = metricsRepository.findById(metricsId).orElseThrow(); // TODO: custom exception
      var linesIterator = br.lines()
          .parallel()
          .map(this::processJsonLine)
          .iterator();
      var valuesIterator = Iterators.paddedPartition(linesIterator, batchSize);

      List<MetricsValue> v;
      while(valuesIterator.hasNext()) {
        v = valuesIterator.next();
        v = v.stream().filter(Objects::nonNull).toList();
        v = metricsValueRepository.saveAll(v);

        var metricsOnValues = v.stream()
            .map( el -> new MetricsOnMetricsValues(metrics, el) )
            .toList();
        metricsOnMetricsValuesRepo.saveAll(metricsOnValues);
      }
      tm.commit(ts);
    } catch (Exception e) {
      tm.rollback(ts);
      throw new RuntimeException("failed to process json", e); // TODO: custom exception?
    }
  }

  private MetricsValue processJsonLine(String line) {
    try {
      return objectMapper.readValue(line, MetricsValue.class);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse the line", e); // TODO: failure during json line parsing
    }
  }
}
