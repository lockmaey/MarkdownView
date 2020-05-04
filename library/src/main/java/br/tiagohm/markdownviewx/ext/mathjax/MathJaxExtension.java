package br.tiagohm.markdownviewx.ext.mathjax;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.html.renderer.NodeRenderer;
import com.vladsch.flexmark.html.renderer.NodeRendererFactory;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.misc.Extension;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.MutableDataHolder;

import br.tiagohm.markdownviewx.ext.mathjax.internal.MathJaxDelimiterProcessor;
import br.tiagohm.markdownviewx.ext.mathjax.internal.MathJaxNodeRenderer;

public class MathJaxExtension implements Parser.ParserExtension, HtmlRenderer.HtmlRendererExtension {
    private MathJaxExtension() {
    }

    public static Extension create() {
        return new MathJaxExtension();
    }

    @Override
    public void rendererOptions(final MutableDataHolder options) {

    }

    @Override
    public void parserOptions(final MutableDataHolder options) {

    }

    @Override
    public void extend(Parser.Builder parserBuilder) {
        parserBuilder.customDelimiterProcessor(new MathJaxDelimiterProcessor());
    }

    @Override
    public void extend(HtmlRenderer.Builder rendererBuilder, String rendererType) {
        switch (rendererType) {
            case "HTML":
                rendererBuilder.nodeRendererFactory(new NodeRendererFactory() {
                    @Override
                    public NodeRenderer apply(DataHolder options) {
                        return new MathJaxNodeRenderer(options);
                    }
                });
                break;
        }
    }
}
