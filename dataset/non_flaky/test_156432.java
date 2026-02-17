class DummyClass_156432 {
    @Test
    public void testInvalidFragmentWithCalendar() {
        assertThrows(IllegalArgumentException.class, () -> DateUtils.getFragmentInMilliseconds(aCalendar, 0));
        assertThrows(IllegalArgumentException.class, () -> DateUtils.getFragmentInSeconds(aCalendar, 0));
        assertThrows(IllegalArgumentException.class, () -> DateUtils.getFragmentInMinutes(aCalendar, 0));
        assertThrows(IllegalArgumentException.class, () -> DateUtils.getFragmentInHours(aCalendar, 0));
        assertThrows(IllegalArgumentException.class, () -> DateUtils.getFragmentInDays(aCalendar, 0));
    }

}