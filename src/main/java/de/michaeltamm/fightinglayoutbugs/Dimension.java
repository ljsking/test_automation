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

/**
 * @author Michael Tamm
 */
public class Dimension {
    public final int width;
    public final int height;

    public Dimension(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public int hashCode() {
        return (width << 16 | height);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Dimension) {
            Dimension other = (Dimension) o;
            return (this.width == other.width && this.height == other.height);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return width + " x " + height;
    }
}
