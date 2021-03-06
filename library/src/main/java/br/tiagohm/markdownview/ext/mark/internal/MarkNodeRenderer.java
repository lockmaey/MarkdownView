package br.tiagohm.markdownview.ext.mark.internal;

import com.vladsch.flexmark.html.HtmlWriter;
import com.vladsch.flexmark.html.renderer.NodeRenderer;
import com.vladsch.flexmark.html.renderer.NodeRendererContext;
import com.vladsch.flexmark.html.renderer.NodeRendererFactory;
import com.vladsch.flexmark.html.renderer.NodeRenderingHandler;
import com.vladsch.flexmark.util.data.DataHolder;

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

import br.tiagohm.markdownview.ext.mark.Mark;

public class MarkNodeRenderer implements NodeRenderer {

    public MarkNodeRenderer(DataHolder options) {
    }

    @Override
    public Set<NodeRenderingHandler<?>> getNodeRenderingHandlers() {
        HashSet<NodeRenderingHandler<?>> set = new HashSet<>();
        set.add(new NodeRenderingHandler<>(Mark.class, new NodeRenderingHandler.CustomNodeRenderer<Mark>() {
            @Override
            public void render(@NotNull Mark node, @NotNull NodeRendererContext context, @NotNull HtmlWriter html) {
                MarkNodeRenderer.this.render(node, context, html);
            }
        }));

        return set;
    }

    private void render(Mark node, NodeRendererContext context, HtmlWriter html) {
        html.srcPos(node.getText()).withAttr().tag("mark");
        context.renderChildren(node);
        html.tag("/mark");
    }

    public static class Factory implements NodeRendererFactory {
        @NotNull
        @Override
        public NodeRenderer apply(@NotNull final DataHolder options) {
            return new MarkNodeRenderer(options);
        }
    }
}
