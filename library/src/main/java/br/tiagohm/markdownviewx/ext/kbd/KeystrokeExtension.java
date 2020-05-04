package br.tiagohm.markdownviewx.ext.kbd;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.misc.Extension;
import com.vladsch.flexmark.util.data.MutableDataHolder;

import br.tiagohm.markdownviewx.ext.kbd.internal.KeystrokeDelimiterProcessor;
import br.tiagohm.markdownviewx.ext.kbd.internal.KeystrokeNodeRenderer;

public class KeystrokeExtension implements Parser.ParserExtension, HtmlRenderer.HtmlRendererExtension {
    private KeystrokeExtension() {
    }

    public static Extension create() {
        return new KeystrokeExtension();
    }

    @Override
    public void rendererOptions(final MutableDataHolder options) {

    }

    @Override
    public void parserOptions(final MutableDataHolder options) {

    }

    @Override
    public void extend(Parser.Builder parserBuilder) {
        parserBuilder.customDelimiterProcessor(new KeystrokeDelimiterProcessor());
    }

    @Override
    public void extend(HtmlRenderer.Builder rendererBuilder, String rendererType) {
        switch (rendererType) {
            case "HTML":
                rendererBuilder.nodeRendererFactory(new KeystrokeNodeRenderer.Factory());
                break;
        }
    }
}
