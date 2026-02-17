class DummyClass_86058 {
    @Test
    public void fieldRequirements() {
        assertThatCode(() -> AggregationFunction.AVG.toSeriesSpec("a", null))
                .hasMessageContaining("<avg>")
                .isInstanceOf(IllegalArgumentException.class);

        assertThatCode(() -> AggregationFunction.CARD.toSeriesSpec("a", null))
                .hasMessageContaining("<card>")
                .isInstanceOf(IllegalArgumentException.class);

        assertThatCode(() -> AggregationFunction.COUNT.toSeriesSpec("a", null))
                .doesNotThrowAnyException();

        assertThatCode(() -> AggregationFunction.MAX.toSeriesSpec("a", null))
                .hasMessageContaining("<max>")
                .isInstanceOf(IllegalArgumentException.class);

        assertThatCode(() -> AggregationFunction.MIN.toSeriesSpec("a", null))
                .hasMessageContaining("<min>")
                .isInstanceOf(IllegalArgumentException.class);

        assertThatCode(() -> AggregationFunction.STDDEV.toSeriesSpec("a", null))
                .hasMessageContaining("<stddev>")
                .isInstanceOf(IllegalArgumentException.class);

        assertThatCode(() -> AggregationFunction.SUM.toSeriesSpec("a", null))
                .hasMessageContaining("<sum>")
                .isInstanceOf(IllegalArgumentException.class);

        assertThatCode(() -> AggregationFunction.SUMOFSQUARES.toSeriesSpec("a", null))
                .hasMessageContaining("<sumofsquares>")
                .isInstanceOf(IllegalArgumentException.class);

        assertThatCode(() -> AggregationFunction.VARIANCE.toSeriesSpec("a", null))
                .hasMessageContaining("<variance>")
                .isInstanceOf(IllegalArgumentException.class);
    }

}