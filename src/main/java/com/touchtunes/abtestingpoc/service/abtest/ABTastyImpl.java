package com.touchtunes.abtestingpoc.service.abtest;

import com.abtasty.flagship.main.Flagship;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Slf4j
@Component
public class ABTastyImpl implements ABTestSolution {

	@Override
	public boolean isMammalFilter() {

		val visitor = Flagship.newVisitor("visitor_unique_id").build();
		try {
			visitor.fetchFlags().get();
		} catch (InterruptedException e) {
			log.info("InterruptedException: {}", e);
			throw new RuntimeException(e);
		} catch (ExecutionException e) {
			log.info("ExecutionException: {}", e);
			throw new RuntimeException(e);
		}
		Boolean featureFlag = visitor.getFlag("M4POC1BE-apply-mammal-filter", false).value(false);
		log.info("featureFlag: {}", featureFlag);
		return featureFlag;
	}
}
