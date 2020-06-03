package br.tiagohm.markdownviewx.ext.mark;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.html.renderer.NodeRenderer;
import com.vladsch.flexmark.html.renderer.NodeRendererFactory;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.MutableDataHolder;
import com.vladsch.flexmark.util.misc.Extension;

import org.jetbrains.annotations.NotNull;

import br.tiagohm.markdownviewx.ext.mark.internal.MarkDelimiterProcessor;
import br.tiagohm.markdownviewx.ext.mark.internal.MarkNodeRenderer;

public class MarkExtension implements Parser.ParserExtension, HtmlRenderer.HtmlRendererExtension {
    private MarkExtension() {
    }

    public static Extension create() {
        return new MarkExtension();
    }

    @Override
    public void rendererOptions(@NotNull final MutableDataHolder options) {

    }

    @Override
    public void parserOptions(final MutableDataHolder options) {

    }

    @Override
    public void extend(Parser.Builder parserBuilder) {
        parserBuilder.customDelimiterProcessor(new MarkDelimiterProcessor());
    }

    @Override
    public void extend(@NotNull HtmlRenderer.Builder rendererBuilder, @NotNull String rendererType) {
        if ("HTML".equals(rendererType)) {
            rendererBuilder.nodeRendererFactory(new NodeRendererFactory() {
                @NotNull
                @Override
                public NodeRenderer apply(@NotNull DataHolder options) {
                    return new MarkNodeRenderer(options);
                }
            });
        }
    }
}
