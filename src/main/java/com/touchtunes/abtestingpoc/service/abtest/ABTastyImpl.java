package com.touchtunes.abtestingpoc.service.abtest;

import com.abtasty.flagship.main.Flagship;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Slf4j
@Component
public class ABTastyImpl implements ABTestSolution {

	@Value("${abtasty.flagKey}")
	private String FLAG_KPI;
	@Override
	public boolean isMammalFilter(String visitorUniqueId) {

		val visitor = Flagship.newVisitor(visitorUniqueId).build();
		try {
			visitor.fetchFlags().get();
		} catch (InterruptedException e) {
			log.info("InterruptedException: {}", e);
			throw new RuntimeException(e);
		} catch (ExecutionException e) {
			log.info("ExecutionException: {}", e);
			throw new RuntimeException(e);
		}
		Boolean featureFlag = visitor.getFlag(FLAG_KPI, false).value(false);
		log.info("featureFlag: {}", featureFlag);
		return featureFlag;
	}
}
