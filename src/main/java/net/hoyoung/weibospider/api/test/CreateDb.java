package net.hoyoung.weibospider.api.test;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

/**
 * Created by hoyoung on 2015/11/23.
 */
public class CreateDb {
    public static void main(String[] args) {
        Configuration conf = new Configuration().configure();
        SchemaExport schemaExport = new SchemaExport(conf);
        schemaExport.drop(true, true);
        schemaExport.create(true, true);
    }
}
