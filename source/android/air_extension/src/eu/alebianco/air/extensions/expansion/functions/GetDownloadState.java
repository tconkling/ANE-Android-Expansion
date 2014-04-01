/*
 * Air Native Extension for Google Analytics on iOS and Android
 * 
 * Author: Alessandro Bianco
 * http://alessandrobianco.eu
 *
 * Copyright (c) 2012 Alessandro Bianco
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
package eu.alebianco.air.extensions.expansion.functions;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.google.android.vending.expansion.downloader.Helpers;

import eu.alebianco.air.extensions.expansion.XAPKContext;
import eu.alebianco.air.extensions.expansion.utils.FREUtils;
import eu.alebianco.air.extensions.expansion.utils.LogLevel;

public class GetDownloadState implements FREFunction {

    @Override
    public FREObject call(FREContext context, FREObject[] args) {

        int state = XAPKContext.client.state;

        int messageID = Helpers.getDownloaderStringResourceIDFromState(state);
        String message = context.getActivity().getString(messageID);

        FREObject result = null;
        try {

            FREObject[] params = new FREObject[]{FREObject.newObject(state), FREObject.newObject(message)};
            result = FREObject.newObject("eu.alebianco.air.extensions.expansion.vo.DownloadState", params);
        } catch (Exception e) {
            FREUtils.logEvent(context, LogLevel.FATAL, "Can't convert data to DownloadState. [%s: %s]", e.getClass(), e.getMessage());
        }

        return result;
    }

}
