package br.tiagohm.markdownview.ext.mathjax;

import com.vladsch.flexmark.util.ast.DelimitedNode;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.sequence.BasedSequence;

import org.jetbrains.annotations.NotNull;

public class MathJax extends Node implements DelimitedNode {
    protected BasedSequence openingMarker = BasedSequence.NULL;
    protected BasedSequence text = BasedSequence.NULL;
    protected BasedSequence closingMarker = BasedSequence.NULL;
    protected String superscriptBlockText;
    protected final boolean isInline;

    public MathJax() {
        isInline = true;
    }

    public MathJax(boolean isInline) {
        this.isInline = isInline;
    }

    public MathJax(BasedSequence chars, boolean isInline) {
        super(chars);
        this.isInline = isInline;
    }

    public MathJax(BasedSequence openingMarker, BasedSequence text, BasedSequence closingMarker, boolean isInline) {
        super(openingMarker.baseSubSequence(openingMarker.getStartOffset(), closingMarker.getEndOffset()));
        this.openingMarker = openingMarker;
        this.text = text;
        this.closingMarker = closingMarker;
        this.isInline = isInline;
    }

    public MathJax(BasedSequence chars, String superscriptBlockText, boolean isInline) {
        super(chars);
        this.superscriptBlockText = superscriptBlockText;
        this.isInline = isInline;
    }

    @NotNull
    @Override
    public BasedSequence[] getSegments() {
        return new BasedSequence[]{openingMarker, text, closingMarker};
    }

    @Override
    public void getAstExtra(@NotNull StringBuilder out) {
        delimitedSegmentSpanChars(out, openingMarker, text, closingMarker, "text");
    }

    public BasedSequence getOpeningMarker() {
        return openingMarker;
    }

    public void setOpeningMarker(BasedSequence openingMarker) {
        this.openingMarker = openingMarker;
    }

    public BasedSequence getText() {
        return text;
    }

    public void setText(BasedSequence text) {
        this.text = text;
    }

    public BasedSequence getClosingMarker() {
        return closingMarker;
    }

    public void setClosingMarker(BasedSequence closingMarker) {
        this.closingMarker = closingMarker;
    }

    public boolean isInline() {
        return isInline;
    }
}
