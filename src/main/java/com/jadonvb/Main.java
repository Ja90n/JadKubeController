package com.jadonvb;

import io.kubernetes.client.Copy;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.KubeConfig;
import io.kubernetes.client.util.Streams;
import io.kubernetes.client.util.exception.CopyNotSupportedException;

import java.io.*;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException, ApiException, InterruptedException, CopyNotSupportedException {

        ApiClient client = ClientBuilder.kubeconfig(KubeConfig.loadKubeConfig(new FileReader("config"))).build();
        Configuration.setDefaultApiClient(client);

        // the CoreV1Api loads default api-client from global configuration.
        CoreV1Api api = new CoreV1Api(client);

        Copy copy = new Copy();
        InputStream dataStream = copy.copyFileFromPod("default", "velocity-de-7c76cc5b46-wrhkt", "/app/");
        Streams.copy(dataStream, System.out);

        copy.copyDirectoryFromPod("default", "velocity-de-7c76cc5b46-wrhkt", null, "/app", Paths.get("C:\\Users\\jadon\\Desktop\\ggs"));

        System.out.println("Done!");

    }
}