package br.tiagohm.markdownview.ext.mathjax;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.html.renderer.NodeRenderer;
import com.vladsch.flexmark.html.renderer.NodeRendererFactory;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.MutableDataHolder;
import com.vladsch.flexmark.util.misc.Extension;

import org.jetbrains.annotations.NotNull;

import br.tiagohm.markdownview.ext.mathjax.internal.MathJaxDelimiterProcessor;
import br.tiagohm.markdownview.ext.mathjax.internal.MathJaxNodeRenderer;

public class MathJaxExtension implements Parser.ParserExtension, HtmlRenderer.HtmlRendererExtension {
    private MathJaxExtension() {
    }

    public static Extension create() {
        return new MathJaxExtension();
    }

    @Override
    public void rendererOptions(@NotNull final MutableDataHolder options) {

    }

    @Override
    public void parserOptions(final MutableDataHolder options) {

    }

    @Override
    public void extend(Parser.Builder parserBuilder) {
        parserBuilder.customDelimiterProcessor(new MathJaxDelimiterProcessor());
    }

    @Override
    public void extend(@NotNull HtmlRenderer.Builder rendererBuilder, @NotNull String rendererType) {
        if ("HTML".equals(rendererType)) {
            rendererBuilder.nodeRendererFactory(new NodeRendererFactory() {
                @NotNull
                @Override
                public NodeRenderer apply(@NotNull DataHolder options) {
                    return new MathJaxNodeRenderer(options);
                }
            });
        }
    }
}
