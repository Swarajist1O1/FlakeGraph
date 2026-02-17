class DummyClass_43042 {
    @Test
    public void testColumnsInReverseOrder()
    {
        assertQuery("SELECT shippriority, clerk, totalprice FROM orders");
    }

}