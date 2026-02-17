class DummyClass_35667 {
  @Test
  public void testDeployApplicationInNamespace() throws Exception {
    NamespaceId namespace = new NamespaceId("Test1");
    NamespaceMeta namespaceMeta = new NamespaceMeta.Builder().setName(namespace).build();
    getNamespaceClient().create(namespaceMeta);
    ClientConfig clientConfig = new ClientConfig.Builder(getClientConfig()).build();
    deployApplication(namespace, AllProgramsApp.class);

    // Check the default namespaces applications to see whether the application wasn't made in the default namespace
    ClientConfig defaultClientConfig = new ClientConfig.Builder(getClientConfig()).build();
    Assert.assertTrue(new ApplicationClient(defaultClientConfig).list(NamespaceId.DEFAULT).isEmpty());

    ApplicationClient applicationClient = new ApplicationClient(clientConfig);
    Assert.assertEquals(AllProgramsApp.NAME, applicationClient.list(namespace).get(0).getName());
    applicationClient.delete(namespace.app(AllProgramsApp.NAME));
    Assert.assertTrue(new ApplicationClient(clientConfig).list(namespace).isEmpty());

  }

}