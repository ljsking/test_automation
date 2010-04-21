/*
 * Copyright 2009 Michael Tamm
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.michaeltamm.fightinglayoutbugs;

import com.thoughtworks.selenium.Selenium;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.util.Collection;

/**
 * @author Michael Tamm
 */
public interface LayoutBugDetector {

    /**
     * Call this method before you call {@link #findLayoutBugsIn} if you
     * want to have screenshots of the detected layout bugs.
     */
    void setScreenshotDir(File screenshotDir);

    /**
     * Finds layout bugs in the web page currently displayed by the given selenium.
     * If you want screenshots for the returned layout bugs,
     * you need to {@link #setScreenshotDir set the screenshot directory} first.
     */
    Collection<LayoutBug> findLayoutBugsIn(Selenium selenium) throws Exception;

    /**
     * Finds layout bugs in the web page currently displayed by the given driver.
     * If you want screenshots for the returned layout bugs,
     * you need to {@link #setScreenshotDir set the screenshot directory} first.
     */
    Collection<LayoutBug> findLayoutBugsIn(WebDriver driver) throws Exception;

    /**
     * Finds layout bugs in the given web page.
     * If you want screenshots for the returned layout bugs,
     * you need to {@link #setScreenshotDir set the screenshot directory} first.
     */
    Collection<LayoutBug> findLayoutBugsIn(WebPage webPage) throws Exception;
}
