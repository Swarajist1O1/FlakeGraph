class DummyClass_156431 {
    @Test
    public void testInvalidFragmentWithDate() {
        assertThrows(IllegalArgumentException.class, () -> DateUtils.getFragmentInMilliseconds(aDate, 0));
        assertThrows(IllegalArgumentException.class, () -> DateUtils.getFragmentInSeconds(aDate, 0));
        assertThrows(IllegalArgumentException.class, () -> DateUtils.getFragmentInMinutes(aDate, 0));
        assertThrows(IllegalArgumentException.class, () -> DateUtils.getFragmentInHours(aDate, 0));
        assertThrows(IllegalArgumentException.class, () -> DateUtils.getFragmentInDays(aDate, 0));
    }

}