package org.example;


import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.configuration.ClientIntelligence;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;
import org.infinispan.client.hotrod.configuration.SaslQop;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {


        ConfigurationBuilder builder = new ConfigurationBuilder();
        builder.addServer()
                .host("sec-datagrid-datagrid.apps.ocp.desenv.com")
                .port(443)
                .security().authentication()
                .username("developer")
                .password("usWQdehzOGGglqip")
                .realm("default")
                .saslQop(SaslQop.AUTH)
                .saslMechanism("SCRAM-SHA-512")
                .ssl()
                .sniHostName("sec-datagrid-datagrid.apps.ocp.desenv.com")
                .trustStoreFileName("/home/sofintech_esalvador/teste-datagrid/src/main/resources/my_truststore.p12")
                .trustStorePassword("123123".toCharArray())
                .trustStoreType("PKCS12");
//

        builder.clientIntelligence(ClientIntelligence.BASIC);

//        builder.remoteCache("centralizador-cache")
//                .templateName(DefaultTemplate.DIST_SYNC);
//        builder.remoteCache("another-cache")
//                .configuration("<infinispan><cache-container><distributed-cache name=\"another-cache\"><encoding media-type=\"application/x-protostream\"/></distributed-cache></cache-container></infinispan>");
        try (RemoteCacheManager cacheManager = new RemoteCacheManager(builder.build())) {
            // Get a remote cache that does not exist.
            // Rather than return null, create the cache from a template.
            RemoteCache<String, String> cache = cacheManager.getCache("centralizador-cache");
            // Store a value.
            cache.put("hello", "world");
            // Retrieve the value and print it.
            System.out.printf("key = %s\n", cache.get("hello"));


        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}