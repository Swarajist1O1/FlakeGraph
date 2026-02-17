class DummyClass_156430 {
    @Test
    public void testNullCalendar() {
        assertThrows(
                IllegalArgumentException.class,
                () -> DateUtils.getFragmentInMilliseconds((Calendar) null, Calendar.MILLISECOND));

        assertThrows(
                IllegalArgumentException.class,
                () -> DateUtils.getFragmentInSeconds((Calendar) null, Calendar.MILLISECOND));

        assertThrows(
                IllegalArgumentException.class,
                () -> DateUtils.getFragmentInMinutes((Calendar) null, Calendar.MILLISECOND));

        assertThrows(
                IllegalArgumentException.class,
                () -> DateUtils.getFragmentInHours((Calendar) null, Calendar.MILLISECOND));

        assertThrows(
                IllegalArgumentException.class,
                () -> DateUtils.getFragmentInDays((Calendar) null, Calendar.MILLISECOND));
    }

}