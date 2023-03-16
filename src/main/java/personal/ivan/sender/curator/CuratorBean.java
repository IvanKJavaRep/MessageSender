package personal.ivan.sender.curator;

import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.zookeeper.curator.ZookeeperCuratorLockProvider;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.time.LocalDateTime;

@Component
public class CuratorBean {
    static byte[] arr = new byte[10];
    static int sleepMsBetweenRetries = 100;
    static int maxRetries = 3;

    @Scheduled(cron = "*/2 * * * * *")
    @SchedulerLock(name = "TaskLock", lockAtLeastFor = "10s", lockAtMostFor = "30s")
    public void scheduledTask() throws Exception {
        RetryPolicy retryPolicy = new RetryNTimes(
                maxRetries, sleepMsBetweenRetries);

        CuratorFramework client = CuratorFrameworkFactory
                .newClient("10.3.63.228:2181", retryPolicy);
        client.start();
        client
                .create()
                .orSetData()
                .forPath("/hbase/namespace/znode", arr);
        FileWriter fileWriter = new FileWriter("C:\\Users\\i.haritonin\\Downloads\\MessageSender\\logs.txt", true);
        fileWriter.write("Service1 " + LocalDateTime.now().toString() + "\n");
        fileWriter.close();
    }

    @Bean(name = "curator")
    public org.apache.curator.framework.CuratorFramework getCuratorBean() throws Exception {
        int sleepMsBetweenRetries = 100;
        int maxRetries = 3;
        RetryPolicy retryPolicy = new RetryNTimes(
                maxRetries, sleepMsBetweenRetries);

        CuratorFramework client = CuratorFrameworkFactory
                .newClient("10.3.63.228:2181", retryPolicy);
        client.start();
        return client;
    }

    @Bean
    public LockProvider lockProvider(@Qualifier("curator") org.apache.curator.framework.CuratorFramework client) {
        return new ZookeeperCuratorLockProvider(client, "/hbase/namespace/znode");
    }
}
