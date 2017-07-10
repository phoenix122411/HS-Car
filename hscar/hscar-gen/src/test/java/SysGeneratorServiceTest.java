import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.agile.service.api.ISysGeneratorService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring.xml" })
public class SysGeneratorServiceTest {
	
	@Autowired
	private ISysGeneratorService sysGeneratorService;
	
	//zip输出路径
	String zipPath = "E://agile-code//code.zip";
	
	//表名
	String[] tableNames = new String[] {"pay_channel_config","pay_cash_serial","pay_total_collect"};
//	String[] tableNames = new String[] {"redis_success_log","redis_error_log"};
//	String[] tableNames = new String[] {"pay_account_bank","pay_account_capital_close",
//			"pay_account_capital_priority","pay_allowance_config","pay_bank_config",
//			"pay_cash_serial","pay_channel_config","pay_charge_config","pay_collect_capital_close",
//			"pay_collect_capital_priority","pay_customer_channel_openinfo","pay_file_collect_temp",
//			"pay_fail_call","pay_nonpreferred_channel_config","pay_system_config","pay_total_collect"};

	@Test
	public void testGeneratorCode() throws IOException {
		byte[] data = sysGeneratorService.generatorCode(tableNames);
		File file = new File(zipPath);
		if(!file.getParentFile().exists()){
			file.getParentFile().mkdirs();
		}
		FileUtils.writeByteArrayToFile(file, data);
		
		//解压zip
		ZipInputStream zip = new ZipInputStream(new FileInputStream(zipPath));
		ZipEntry entry;
		while((entry = zip.getNextEntry()) != null) {
			file = new File("E://agile-code//" + entry.getName());
			if(!file.getParentFile().exists()){
				file.getParentFile().mkdirs();
			}
			FileOutputStream out = new FileOutputStream(file);
			IOUtils.copy(zip, out);
		}
		zip.close();
	}
}