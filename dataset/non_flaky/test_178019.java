class DummyClass_178019 {
    @Test
    public void testMediaPlayPauseButtonOnPlay() {
        PlaybackControlsRow row = new PlaybackControlsRow();
        glue.setControlsRow(row);
        SparseArrayObjectAdapter adapter = (SparseArrayObjectAdapter)
                row.getPrimaryActionsAdapter();
        PlaybackControlsRow.MultiAction playPause = (PlaybackControlsRow.MultiAction) adapter
                .lookup(PlaybackControlGlue.ACTION_PLAY_PAUSE);

        glue.onActionClicked(playPause);
        assertEquals(PlaybackControlGlue.PLAYBACK_SPEED_NORMAL, glue.getCurrentSpeedId());
        glue.onKey(null, KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE, new KeyEvent(KeyEvent.ACTION_DOWN,
                KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE));
        assertEquals(PlaybackControlGlue.PLAYBACK_SPEED_PAUSED, glue.getCurrentSpeedId());
    }

}