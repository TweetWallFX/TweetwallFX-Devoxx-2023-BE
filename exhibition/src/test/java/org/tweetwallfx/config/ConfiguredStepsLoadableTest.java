/*
 * MIT License
 *
 * Copyright (c) 2022 TweetWallFX
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.tweetwallfx.config;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.HashSet;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.tweetwallfx.stepengine.api.Step;
import org.tweetwallfx.stepengine.api.config.StepEngineSettings;

class ConfiguredStepsLoadableTest {

    static Stream<Arguments> parameters() {
        return Configuration.getInstance()
                .getConfigTyped("stepEngine", StepEngineSettings.class)
                .steps()
                .stream()
                .map(StepEngineSettings.StepDefinition::getStepClassName)
                .distinct()
                .sorted()
                .map(cn -> arguments(cn));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("parameters")
    void checkTestCase(final String stepName) throws Exception {
        final Set<String> availableStepFactories = new HashSet<>();
        for (final Step.Factory o : ServiceLoader.load(Step.Factory.class)) {
            availableStepFactories.add(o.getStepClass().getName());
        }

        assertThat(availableStepFactories)
                .withFailMessage("None of the available step factories can create the step " + stepName)
                .contains(stepName);
    }
}
