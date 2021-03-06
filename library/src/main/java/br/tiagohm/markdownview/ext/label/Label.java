package br.tiagohm.markdownview.ext.label;

import com.vladsch.flexmark.util.ast.DelimitedNode;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.sequence.BasedSequence;

import org.jetbrains.annotations.NotNull;

public class Label extends Node implements DelimitedNode {
    protected BasedSequence openingMarker = BasedSequence.NULL;
    protected BasedSequence text = BasedSequence.NULL;
    protected BasedSequence closingMarker = BasedSequence.NULL;
    protected String superscriptBlockText;
    protected int type = 0;

    public Label() {
    }

    public Label(BasedSequence chars) {
        super(chars);
    }

    public Label(int type, BasedSequence openingMarker, BasedSequence text, BasedSequence closingMarker) {
        super(openingMarker.baseSubSequence(openingMarker.getStartOffset(), closingMarker.getEndOffset()));
        this.type = type;
        this.openingMarker = openingMarker;
        this.text = text;
        this.closingMarker = closingMarker;
    }

    public Label(BasedSequence chars, String superscriptBlockText) {
        super(chars);
        this.superscriptBlockText = superscriptBlockText;
    }

    public int getType() {
        return type;
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
}
