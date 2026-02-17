class DummyClass_178018 {
    @Test
    public void testMediaPlayPauseButtonOnFF() {
        PlaybackControlsRow row = new PlaybackControlsRow();
        glue.setControlsRow(row);
        SparseArrayObjectAdapter adapter = (SparseArrayObjectAdapter)
                row.getPrimaryActionsAdapter();
        PlaybackControlsRow.MultiAction playPause = (PlaybackControlsRow.MultiAction) adapter
                .lookup(PlaybackControlGlue.ACTION_PLAY_PAUSE);
        PlaybackControlsRow.MultiAction fastForward = (PlaybackControlsRow.MultiAction) adapter
                .lookup(PlaybackControlGlue.ACTION_FAST_FORWARD);

        glue.onActionClicked(playPause);
        glue.onActionClicked(fastForward);
        assertEquals(PlaybackControlGlue.PLAYBACK_SPEED_FAST_L0, glue.getCurrentSpeedId());
        glue.onKey(null, KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE, new KeyEvent(KeyEvent.ACTION_DOWN,
                KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE));
        assertEquals(PlaybackControlGlue.PLAYBACK_SPEED_NORMAL, glue.getCurrentSpeedId());
    }

}