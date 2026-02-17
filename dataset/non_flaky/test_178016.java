class DummyClass_178016 {
    @Test
    public void testMediaPlayButtonOnPlay() {
        PlaybackControlsRow row = new PlaybackControlsRow();
        glue.setControlsRow(row);
        SparseArrayObjectAdapter adapter = (SparseArrayObjectAdapter)
                row.getPrimaryActionsAdapter();
        PlaybackControlsRow.MultiAction playPause = (PlaybackControlsRow.MultiAction) adapter
                .lookup(PlaybackControlGlue.ACTION_PLAY_PAUSE);

        glue.onActionClicked(playPause);
        assertEquals(PlaybackControlGlue.PLAYBACK_SPEED_NORMAL, glue.getCurrentSpeedId());
        glue.onKey(null, KeyEvent.KEYCODE_MEDIA_PLAY, new KeyEvent(KeyEvent.ACTION_DOWN,
                KeyEvent.KEYCODE_MEDIA_PLAY));
        assertEquals(PlaybackControlGlue.PLAYBACK_SPEED_NORMAL, glue.getCurrentSpeedId());
    }

}