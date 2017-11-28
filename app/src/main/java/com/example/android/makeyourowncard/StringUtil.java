package com.example.android.makeyourowncard;

import android.text.TextPaint;
import android.text.style.URLSpan;
import android.text.Spannable;

public class StringUtil extends URLSpan {
    private StringUtil(String pUrl) {
        super(pUrl);
    }

    public void updateDrawState(TextPaint pDrawState) {
        super.updateDrawState(pDrawState);
        pDrawState.setUnderlineText(false);
    }

    /**
     * Removes URL underlines in a string by replacing URLSpan occurrences by
     * URLSpanNoUnderline objects.
     *
     * @param pText A Spannable object. For example, a TextView casted as
     *               Spannable.
     *
     * based on https://www.evilcodingmonkey.com/2011/06/07/remove-underline-from-android-textview-link/
     */
    public static void removeUnderlines(Spannable pText) {
        URLSpan[] spans = pText.getSpans(0, pText.length(), URLSpan.class);

        for(URLSpan span:spans) {
            int start = pText.getSpanStart(span);
            int end = pText.getSpanEnd(span);
            pText.removeSpan(span);
            span = new StringUtil(span.getURL());
            pText.setSpan(span, start, end,0);
        }
    }
}
