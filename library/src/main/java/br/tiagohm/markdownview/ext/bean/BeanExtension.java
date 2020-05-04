package br.tiagohm.markdownview.ext.bean;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.data.DataKey;
import com.vladsch.flexmark.util.data.MutableDataHolder;
import com.vladsch.flexmark.util.misc.Extension;

import br.tiagohm.markdownview.MarkdownView;
import br.tiagohm.markdownview.ext.bean.internal.BeanDelimiterProcessor;
import br.tiagohm.markdownview.ext.bean.internal.BeanNodeRenderer;

public class BeanExtension implements Parser.ParserExtension, HtmlRenderer.HtmlRendererExtension {
    public static final DataKey<MarkdownView> BEAN_VIEW = new DataKey<>("BEAN_VIEW", (MarkdownView) null);

    private BeanExtension() {
    }

    public static Extension create() {
        return new BeanExtension();
    }

    @Override
    public void rendererOptions(final MutableDataHolder options) {

    }

    @Override
    public void parserOptions(final MutableDataHolder options) {

    }

    @Override
    public void extend(Parser.Builder parserBuilder) {
        parserBuilder.customDelimiterProcessor(new BeanDelimiterProcessor());
    }

    @Override
    public void extend(HtmlRenderer.Builder rendererBuilder, String rendererType) {
        switch (rendererType) {
            case "HTML":
                rendererBuilder.nodeRendererFactory(new BeanNodeRenderer.Factory());
                break;
        }
    }
}
