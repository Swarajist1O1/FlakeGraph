class DummyClass_35732 {
  @Test
  public void testGetResolution() {
    MetricsQueryHelper helper = new MetricsQueryHelper(null, CConfiguration.create());
    try {
      // test  start > end time
      helper.getResolution("auto", 10000L, 100L);
      Assert.fail();
    } catch (IllegalArgumentException e) {
      // expected
    }

    try {
      // test resolution is auto, but not both start and end time are provided
      helper.getResolution("auto", null, 200L);
      Assert.fail();
    } catch (IllegalArgumentException e) {
      // expected
    }

    try {
      // test resolution is auto, but not both start and end time are provided
      helper.getResolution("auto", 200L, null);
      Assert.fail();
    } catch (IllegalArgumentException e) {
      // expected
    }

    try {
      // test non-existing resolution
      helper.getResolution("6s", 400L, 600L);
      Assert.fail();
    } catch (IllegalArgumentException e) {
      // expected
    }

    // test specific resolution
    Assert.assertEquals(1, helper.getResolution("1s", 100L, 10000L).intValue());
    Assert.assertEquals(60, helper.getResolution("1m", 1000L, 100000L).intValue());
    Assert.assertEquals(3600, helper.getResolution("1h", 100L, 10000L).intValue());
    Assert.assertEquals(60, helper.getResolution("60s", 100L, 10000L).intValue());

    // test resolution is auto
    // if 0 < ts diff <= 600, second resolution will be used
    Assert.assertEquals(1, helper.getResolution("auto", 0L, 300L).intValue());
    Assert.assertEquals(1, helper.getResolution("auto", 10000L, 10300L).intValue());
    Assert.assertEquals(1, helper.getResolution("auto", 0L, 600L).intValue());
    Assert.assertEquals(1, helper.getResolution("auto", 1000L, 1600L).intValue());

    // if 600 < ts diff <= 36000, minute resolution will be used
    Assert.assertEquals(60, helper.getResolution("auto", 0L, 601L).intValue());
    Assert.assertEquals(60, helper.getResolution("auto", 10000L, 10601L).intValue());
    Assert.assertEquals(60, helper.getResolution("auto", 0L, 36000L).intValue());
    Assert.assertEquals(60, helper.getResolution("auto", 10000L, 46000L).intValue());

    // if ts > 36000, hour resolution will be used
    Assert.assertEquals(3600, helper.getResolution("auto", 0L, 36001L).intValue());
    Assert.assertEquals(3600, helper.getResolution("auto", 1000L, 10000000L).intValue());

    // if resolution is null, and both start and end time provided, the logic should be same as auto
    Assert.assertEquals(1, helper.getResolution(null, 0L, 300L).intValue());
    Assert.assertEquals(1, helper.getResolution(null, 10000L, 10300L).intValue());
    Assert.assertEquals(1, helper.getResolution(null, 0L, 600L).intValue());
    Assert.assertEquals(1, helper.getResolution(null, 1000L, 1600L).intValue());
    Assert.assertEquals(60, helper.getResolution(null, 0L, 601L).intValue());
    Assert.assertEquals(60, helper.getResolution(null, 10000L, 10601L).intValue());
    Assert.assertEquals(60, helper.getResolution(null, 0L, 36000L).intValue());
    Assert.assertEquals(60, helper.getResolution(null, 10000L, 46000L).intValue());
    Assert.assertEquals(3600, helper.getResolution(null, 0L, 36001L).intValue());
    Assert.assertEquals(3600, helper.getResolution(null, 1000L, 10000000L).intValue());

    // if resolution is null, and either timestamp is not specified, minimum resolution will be used
    Assert.assertEquals(1, helper.getResolution(null, 0L, null).intValue());
    Assert.assertEquals(1, helper.getResolution(null, null, 10000000L).intValue());
  }

}