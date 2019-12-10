package sample.batch;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/config/batch/job/sample-job.xml"})
public class SampleBatchJobTest {

	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;
	
	@Test
	public void launchJob() throws Exception {
		JobParametersBuilder builder = new JobParametersBuilder();
		
		builder.addString("currentTime", (new Date()).toString());
		JobExecution jobExecution = jobLauncherTestUtils.launchJob(builder.toJobParameters());
		Assert.assertEquals(ExitStatus.COMPLETED, jobExecution.getStatus());
	}
}
