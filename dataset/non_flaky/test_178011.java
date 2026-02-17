class DummyClass_178011 {
    @Test
    public void testRewindAbortKeyCodes() {
        PlaybackControlsRow row = new PlaybackControlsRow();
        glue.setControlsRow(row);
        SparseArrayObjectAdapter adapter = (SparseArrayObjectAdapter)
                row.getPrimaryActionsAdapter();
        PlaybackControlsRow.MultiAction playPause = (PlaybackControlsRow.MultiAction) adapter
                .lookup(PlaybackControlGlue.ACTION_PLAY_PAUSE);
        PlaybackControlsRow.MultiAction fastForward = (PlaybackControlsRow.MultiAction) adapter
                .lookup(PlaybackControlGlue.ACTION_FAST_FORWARD);
        PlaybackControlsRow.MultiAction rewind = (PlaybackControlsRow.MultiAction) adapter
                .lookup(PlaybackControlGlue.ACTION_REWIND);

        glue.onActionClicked(playPause);
        assertTrue(glue.isMediaPlaying());
        assertEquals(PlaybackControlGlue.PLAYBACK_SPEED_NORMAL, glue.getCurrentSpeedId());
        assertEquals(0, fastForward.getIndex());
        assertEquals(0, rewind.getIndex());

        // Testing keycodes that will not abort seek
        final int[] noAbortSeekKeyCodes = new int[] {
                KeyEvent.KEYCODE_DPAD_CENTER,
                KeyEvent.KEYCODE_ENTER
        };
        for (int i = 0; i < noAbortSeekKeyCodes.length; i++) {
            glue.onActionClicked(rewind);
            assertEquals(-PlaybackControlGlue.PLAYBACK_SPEED_FAST_L0, glue.getCurrentSpeedId());
            assertEquals(0, fastForward.getIndex());
            assertEquals(1, rewind.getIndex());
            KeyEvent kv = new KeyEvent(KeyEvent.ACTION_DOWN, noAbortSeekKeyCodes[i]);
            glue.onKey(null, noAbortSeekKeyCodes[i], kv);
            assertEquals(-PlaybackControlGlue.PLAYBACK_SPEED_FAST_L0, glue.getCurrentSpeedId());
            glue.onActionClicked(playPause);
            assertEquals(PlaybackControlGlue.PLAYBACK_SPEED_NORMAL, glue.getCurrentSpeedId());
        }

        // Testing abortSeekKeyCodes
        final int[] abortSeekKeyCodes = new int[] {
                KeyEvent.KEYCODE_DPAD_UP,
                KeyEvent.KEYCODE_DPAD_DOWN,
                KeyEvent.KEYCODE_DPAD_RIGHT,
                KeyEvent.KEYCODE_DPAD_LEFT,
                KeyEvent.KEYCODE_BACK,
                KeyEvent.KEYCODE_ESCAPE
        };
        for (int i = 0; i < abortSeekKeyCodes.length; i++) {
            glue.onActionClicked(rewind);
            assertEquals(-PlaybackControlGlue.PLAYBACK_SPEED_FAST_L0, glue.getCurrentSpeedId());
            assertEquals(0, fastForward.getIndex());
            assertEquals(1, rewind.getIndex());
            KeyEvent kv = new KeyEvent(KeyEvent.ACTION_DOWN, abortSeekKeyCodes[i]);
            glue.onKey(null, abortSeekKeyCodes[i], kv);
            assertEquals(PlaybackControlGlue.PLAYBACK_SPEED_NORMAL, glue.getCurrentSpeedId());
            assertEquals(0, fastForward.getIndex());
            assertEquals(0, rewind.getIndex());
        }
    }

}