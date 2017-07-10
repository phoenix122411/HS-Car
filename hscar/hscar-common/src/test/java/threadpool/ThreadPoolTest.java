package threadpool;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Spring线程池的测试类
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-threadpool.xml" }) // 测试环境spring容器
//@ContextConfiguration(locations = { "classpath:spring.xml" }) // 正式环境spring容器
public class ThreadPoolTest {
	@Autowired
	ThreadPoolTaskExecutor threadPoolTaskExecutor;

	@Test
	public void test1() {
		// 使用线程池多线程处理
		threadPoolTaskExecutor.execute(new Runnable() {
			public void run() {
				try {
					System.out.println("test ");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
