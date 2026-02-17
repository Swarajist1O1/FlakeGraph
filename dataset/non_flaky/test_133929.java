class DummyClass_133929 {
@Test(groups = "unit", testName = "JenkinsApiMetadataTest")
   public void testEtcdApiRegistered() {
      ApiMetadata api = Apis.withId("jenkins");

      assertNotNull(api);
      assertTrue(api instanceof JenkinsApiMetadata);
      assertEquals(api.getId(), "jenkins");
   }

}