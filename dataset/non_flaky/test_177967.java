class DummyClass_177967 {
    @Test
    public void testMetaDataTargets() {
        Uri actual;

        actual = FileProvider.getUriForFile(mContext, TEST_AUTHORITY,
                new File("/proc/version"));
        assertEquals("content://moocow/test_root/proc/version", actual.toString());

        actual = FileProvider.getUriForFile(mContext, TEST_AUTHORITY,
                new File("/proc/1/mountinfo"));
        assertEquals("content://moocow/test_init/mountinfo", actual.toString());

        actual = FileProvider.getUriForFile(mContext, TEST_AUTHORITY,
                buildPath(mContext.getFilesDir(), "meow"));
        assertEquals("content://moocow/test_files/meow", actual.toString());

        actual = FileProvider.getUriForFile(mContext, TEST_AUTHORITY,
                buildPath(mContext.getFilesDir(), "thumbs", "rawr"));
        assertEquals("content://moocow/test_thumbs/rawr", actual.toString());

        actual = FileProvider.getUriForFile(mContext, TEST_AUTHORITY,
                buildPath(mContext.getCacheDir(), "up", "down"));
        assertEquals("content://moocow/test_cache/up/down", actual.toString());

        actual = FileProvider.getUriForFile(mContext, TEST_AUTHORITY,
                buildPath(Environment.getExternalStorageDirectory(), "Android", "obb", "foobar"));
        assertEquals("content://moocow/test_external/Android/obb/foobar", actual.toString());

        File[] externalFilesDirs = ContextCompat.getExternalFilesDirs(mContext, null);
        actual = FileProvider.getUriForFile(mContext, TEST_AUTHORITY,
            buildPath(externalFilesDirs[0], "foo", "bar"));
        assertEquals("content://moocow/test_external_files/foo/bar", actual.toString());

        File[] externalCacheDirs = ContextCompat.getExternalCacheDirs(mContext);
        actual = FileProvider.getUriForFile(mContext, TEST_AUTHORITY,
            buildPath(externalCacheDirs[0], "foo", "bar"));
        assertEquals("content://moocow/test_external_cache/foo/bar", actual.toString());
    }

}