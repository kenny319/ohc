/*
 *      Copyright (C) 2014 Robert Stupp, Koeln, Germany, robert-stupp.de
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package org.caffinitas.ohc.benchmark;

import java.util.concurrent.TimeUnit;

class ReadTask implements Runnable
{
    private final long key;

    public ReadTask(long key)
    {
        this.key = key;
    }

    public void run()
    {
        try
        {
            long t0 = Shared.ntime();
            Shared.cache.get(key);
            long t = Shared.ntime() - t0;
            Shared.readTimer.update(t, TimeUnit.NANOSECONDS);
        }
        catch (Throwable t)
        {
            t.printStackTrace();
            Shared.fatal.set(true);
        }
    }
}
