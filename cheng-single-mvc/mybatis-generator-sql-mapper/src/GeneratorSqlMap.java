
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author cheng
 *         2018/10/3 12:55
 */
public class GeneratorSqlMap {

    public void generator() throws Exception {

        List<String> warnings = new ArrayList<>();
        boolean overwrite = true;

        //指定 逆向工程配置文件
        File configFile = new File("cheng-single-mvc/mybatis-generator-sql-mapper/generatorConfig.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,callback, warnings);
        myBatisGenerator.generate(null);
    }

    public static void main(String[] args) {
        try {
            GeneratorSqlMap generatorSqlMap = new GeneratorSqlMap();
            generatorSqlMap.generator();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
