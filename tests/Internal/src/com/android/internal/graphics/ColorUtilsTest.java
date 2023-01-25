/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.android.internal.graphics;

import static org.junit.Assert.assertTrue;

import android.graphics.Color;

import androidx.test.filters.SmallTest;

import org.junit.Test;

@SmallTest
public class ColorUtilsTest {

    @Test
    public void calculateMinimumBackgroundAlpha_satisfiestContrast() {

        int alpha = ColorUtils.calculateMinimumBackgroundAlpha(Color.WHITE, Color.BLACK, 4.5f);
        assertTrue("Alpha doesn't need to be 255 to satisfy contrast", alpha < 255);

        int worstCase = ColorUtils.blendARGB(Color.WHITE, Color.BLACK, alpha/255f);
        worstCase = ColorUtils.setAlphaComponent(worstCase, 255);
        double contrast = ColorUtils.calculateContrast(Color.WHITE, worstCase);
        assertTrue("Blended color should satisfy contrast", contrast >= 4.5);

    }
}
