class DummyClass_178017 {
    @Test
    public void testMediaPlayButtonOnPause() {
        PlaybackControlsRow row = new PlaybackControlsRow();
        glue.setControlsRow(row);
        SparseArrayObjectAdapter adapter = (SparseArrayObjectAdapter)
                row.getPrimaryActionsAdapter();
        PlaybackControlsRow.MultiAction playPause = (PlaybackControlsRow.MultiAction) adapter
                .lookup(PlaybackControlGlue.ACTION_PLAY_PAUSE);

        glue.onActionClicked(playPause);
        glue.onActionClicked(playPause);
        assertEquals(PlaybackControlGlue.PLAYBACK_SPEED_PAUSED, glue.getCurrentSpeedId());
        glue.onKey(null, KeyEvent.KEYCODE_MEDIA_PLAY, new KeyEvent(KeyEvent.ACTION_DOWN,
                KeyEvent.KEYCODE_MEDIA_PLAY));
        assertEquals(PlaybackControlGlue.PLAYBACK_SPEED_NORMAL, glue.getCurrentSpeedId());
    }

}