package com.enzulode.metrics.crud.file.processor;

import java.io.InputStream;

public interface FileProcessor {

  void processCsv(Long metricsId, InputStream is);
  void processJson(Long metricsId, InputStream is);
}
