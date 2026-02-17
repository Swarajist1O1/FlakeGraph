class DummyClass_148864 {
    @Test
    public void IsActivityExtendedTypeNoMatch() {
        class MyActivity extends Activity {
            @Override
            public boolean isActivity(String activityType) {
                return super.isActivity(activityType);
            }

}