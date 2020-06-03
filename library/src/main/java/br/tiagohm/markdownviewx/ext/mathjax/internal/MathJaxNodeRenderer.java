package br.tiagohm.markdownviewx.ext.mathjax.internal;

import com.vladsch.flexmark.html.HtmlWriter;
import com.vladsch.flexmark.html.renderer.NodeRenderer;
import com.vladsch.flexmark.html.renderer.NodeRendererContext;
import com.vladsch.flexmark.html.renderer.NodeRendererFactory;
import com.vladsch.flexmark.html.renderer.NodeRenderingHandler;
import com.vladsch.flexmark.util.data.DataHolder;

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

import br.tiagohm.markdownviewx.ext.mathjax.MathJax;

public class MathJaxNodeRenderer implements NodeRenderer {

    public MathJaxNodeRenderer(DataHolder options) {
    }

    @Override
    public Set<NodeRenderingHandler<?>> getNodeRenderingHandlers() {
        HashSet<NodeRenderingHandler<?>> set = new HashSet<>();
        set.add(new NodeRenderingHandler<>(MathJax.class, new NodeRenderingHandler.CustomNodeRenderer<MathJax>() {
            @Override
            public void render(@NotNull MathJax node, @NotNull NodeRendererContext context, @NotNull HtmlWriter html) {
                MathJaxNodeRenderer.this.render(node, context, html);
            }
        }));

        return set;
    }

    private void render(MathJax node, NodeRendererContext context, HtmlWriter html) {
        html.withAttr().attr("class", "math").tag("span");
        if (node.isInline()) {
            html.append("\\(");
        } else {
            html.append("$$");
        }
        context.renderChildren(node);
        if (node.isInline()) {
            html.append("\\)");
        } else {
            html.append("$$");
        }
        html.tag("/span");
    }

    public static class Factory implements NodeRendererFactory {
        @NotNull
        @Override
        public NodeRenderer apply(@NotNull final DataHolder options) {
            return new MathJaxNodeRenderer(options);
        }
    }
}
