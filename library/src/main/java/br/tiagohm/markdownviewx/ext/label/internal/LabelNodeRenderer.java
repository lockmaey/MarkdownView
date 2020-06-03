package br.tiagohm.markdownviewx.ext.label.internal;

import com.vladsch.flexmark.html.HtmlWriter;
import com.vladsch.flexmark.html.renderer.NodeRenderer;
import com.vladsch.flexmark.html.renderer.NodeRendererContext;
import com.vladsch.flexmark.html.renderer.NodeRendererFactory;
import com.vladsch.flexmark.html.renderer.NodeRenderingHandler;
import com.vladsch.flexmark.util.data.DataHolder;

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

import br.tiagohm.markdownviewx.ext.label.Label;


public class LabelNodeRenderer implements NodeRenderer {

    public LabelNodeRenderer(DataHolder options) {
    }

    @Override
    public Set<NodeRenderingHandler<?>> getNodeRenderingHandlers() {
        HashSet<NodeRenderingHandler<?>> set = new HashSet<>();
        set.add(new NodeRenderingHandler<>(Label.class, new NodeRenderingHandler.CustomNodeRenderer<Label>() {
            @Override
            public void render(@NotNull Label node, @NotNull NodeRendererContext context, @NotNull HtmlWriter html) {
                LabelNodeRenderer.this.render(node, context, html);
            }
        }));

        return set;
    }

    private void render(Label node, NodeRendererContext context, HtmlWriter html) {
        if (node.getType() == 3) html.attr("class", "lbl-success");
        else if (node.getType() == 4) html.attr("class", "lbl-warning");
        else if (node.getType() == 5) html.attr("class", "lbl-danger");
        html.withAttr().tag("lbl");
        context.renderChildren(node);
        html.tag("/lbl");
    }

    public static class Factory implements NodeRendererFactory {
        @NotNull
        @Override
        public NodeRenderer apply(@NotNull final DataHolder options) {
            return new LabelNodeRenderer(options);
        }
    }
}
