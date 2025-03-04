package com.enzulode.metrics.crud.file.listener;

import com.enzulode.metrics.crud.event.FileFailedEvent;
import com.enzulode.metrics.crud.event.FileSucceedEvent;
import com.enzulode.metrics.crud.event.NewFileEvent;
import com.enzulode.metrics.crud.event.NewFileEvent.SupportedFileType;
import com.enzulode.metrics.crud.file.processor.FileProcessor;
import com.enzulode.metrics.crud.integration.rabbitmq.RabbitMQProducer;
import com.enzulode.metrics.crud.integration.s3.S3OperationsClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.internal.util.FileUtils;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@Slf4j
@RequiredArgsConstructor
public class NewFileEventListener {

  private static final String TMP_FOLDER_NAME = "tmp";

  @Value("${spring.rabbitmq.mapping.file-events.succeed-file.exchange}") private String succeedExchange;
  @Value("${spring.rabbitmq.mapping.file-events.failed-file.exchange}") private String failedExchange;

  @Value("${spring.rabbitmq.mapping.file-events.succeed-file.routing-key}") private String succeedRoutingKey;
  @Value("${spring.rabbitmq.mapping.file-events.failed-file.routing-key}") private String failedRoutingKey;

  private final RabbitMQProducer producer;
  private final FileProcessor fileProcessor;
  private final S3OperationsClient client;

  @RabbitListener(queues = "${spring.rabbitmq.mapping.file-events.new-file.queue}")
  public void process(NewFileEvent event) {
    log.info("Processing new file event");

    try {
      var is = client.get(TMP_FOLDER_NAME, FileUtils.getFilename(event.objectName()));

      if (event.type() == SupportedFileType.JSON) {
        fileProcessor.processJson(event.metricsId(), is);
      } else {
        fileProcessor.processCsv(event.metricsId(), is);
      }

      var succeedEvent = new FileSucceedEvent(event.objectName(), event.owner());
      producer.send(succeedExchange, succeedRoutingKey, succeedEvent);
    } catch (Exception e) {
      log.error("Error processing new file event", e);
      var failedEvent = new FileFailedEvent(event.objectName(), event.owner(), e.getMessage());
      producer.send(failedExchange, failedRoutingKey, failedEvent);
    }
  }
}
