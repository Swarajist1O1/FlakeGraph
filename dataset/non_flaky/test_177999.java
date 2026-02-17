class DummyClass_177999 {
    @Test
    public void setupTest() {
        double delta = 0.0002;
        DetailsParallaxManager dpm = new DetailsParallaxManager(
                mActivity.getDetailsFragment().getRowsFragment().getVerticalGridView());

        assertNotNull(dpm.getParallax());

        ParallaxRecyclerViewSource.ChildPositionProperty frameTop = dpm.getFrameTop();
        assertEquals(0f, frameTop.getFraction(), delta);
        assertEquals(0f, frameTop.getAdapterPosition(), delta);


        ParallaxRecyclerViewSource.ChildPositionProperty frameBottom = dpm.getFrameBottom();
        assertEquals(1f, frameBottom.getFraction(), delta);
        assertEquals(0f, frameBottom.getAdapterPosition(), delta);
    }

}