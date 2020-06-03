package br.tiagohm.markdownviewx.ext.label;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.data.MutableDataHolder;
import com.vladsch.flexmark.util.misc.Extension;

import org.jetbrains.annotations.NotNull;

import br.tiagohm.markdownviewx.ext.label.internal.LabelDelimiterProcessor;
import br.tiagohm.markdownviewx.ext.label.internal.LabelNodeRenderer;

public class LabelExtension implements Parser.ParserExtension, HtmlRenderer.HtmlRendererExtension {
    private LabelExtension() {
    }

    public static Extension create() {
        return new LabelExtension();
    }

    @Override
    public void rendererOptions(@NotNull final MutableDataHolder options) {

    }

    @Override
    public void parserOptions(final MutableDataHolder options) {

    }

    @Override
    public void extend(Parser.Builder parserBuilder) {
        parserBuilder.customDelimiterProcessor(new LabelDelimiterProcessor());
    }

    @Override
    public void extend(@NotNull HtmlRenderer.Builder rendererBuilder, @NotNull String rendererType) {
        if ("HTML".equals(rendererType)) {
            rendererBuilder.nodeRendererFactory(new LabelNodeRenderer.Factory());
        }
    }
}
