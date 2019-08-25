package com.anz.magneto.autoconfigure;

import com.anz.magneto.model.payment.ComAnzPmtAddRqType;
import com.ctc.wstx.api.WstxInputProperties;
import com.ctc.wstx.stax.WstxInputFactory;
import com.ctc.wstx.stax.WstxOutputFactory;
import com.fasterxml.jackson.dataformat.xml.XmlFactory;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import java.io.File;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(value = {XmlFactory.class, WstxInputFactory.class})
@AutoConfigureAfter(JacksonAutoConfiguration.class)
@Slf4j
public class JacksonXMLAutoConfiguration {
  @Bean
  @ConditionalOnMissingBean
  public XmlMapper xmlMapper() {
    WstxInputFactory inputFactory = new WstxInputFactory();
    inputFactory.configureForSpeed();
    inputFactory.setProperty(WstxInputProperties.P_MAX_ATTRIBUTE_SIZE, 256);
    inputFactory.setProperty(WstxInputProperties.P_MAX_ATTRIBUTES_PER_ELEMENT, 8);

    WstxOutputFactory outputFactory = new WstxOutputFactory();
    outputFactory.configureForSpeed();

    XmlFactory xmlFactory = new XmlFactory(inputFactory, outputFactory);
    XmlMapper xmlMapper = new XmlMapper(xmlFactory);
    xmlMapper.registerModule(new JaxbAnnotationModule());
    log.info("new instance of XmlMapper version {} created", xmlMapper.version());
    return xmlMapper;
  }
}
