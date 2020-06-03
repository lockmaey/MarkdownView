package br.tiagohm.markdownviewx.ext.video;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.data.MutableDataHolder;
import com.vladsch.flexmark.util.misc.Extension;

import org.jetbrains.annotations.NotNull;

import br.tiagohm.markdownviewx.ext.video.internal.VideoLinkNodePostProcessor;
import br.tiagohm.markdownviewx.ext.video.internal.VideoLinkNodeRenderer;

public class VideoLinkExtension implements Parser.ParserExtension, HtmlRenderer.HtmlRendererExtension {
    private VideoLinkExtension() {
    }

    public static Extension create() {
        return new VideoLinkExtension();
    }

    @Override
    public void extend(Parser.Builder parserBuilder) {
        parserBuilder.postProcessorFactory(new VideoLinkNodePostProcessor.Factory(parserBuilder));
    }

    @Override
    public void rendererOptions(@NotNull final MutableDataHolder options) {

    }

    @Override
    public void parserOptions(final MutableDataHolder options) {

    }

    @Override
    public void extend(@NotNull HtmlRenderer.Builder rendererBuilder, String rendererType) {
        if (rendererType.equals("HTML")) {
            rendererBuilder.nodeRendererFactory(new VideoLinkNodeRenderer.Factory());
        }
    }
}
