class DummyClass_21244 {
    @Test
    public void int_persistWhileDisabled_notPersisted() {
        mPreference.setPersistent(false);

        boolean wasPersisted = mPreference.putInt(1);

        assertFalse(wasPersisted);
        assertEquals(-1, mSharedPref.getLong(KEY, -1));
    }

}