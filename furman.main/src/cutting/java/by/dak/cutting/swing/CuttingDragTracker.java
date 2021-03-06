/*
 * @(#)DefaultDragTracker.java
 *
 * Copyright (c) 1996-2010 by the original authors of JHotDraw
 * and all its contributors.
 * All rights reserved.
 *
 * The copyright of this software is owned by the authors and  
 * contributors of the JHotDraw project ("the copyright holders").  
 * You may not use, copy or modify this software, except in  
 * accordance with the license agreement you entered into with  
 * the copyright holders. For details see accompanying license terms. 
 */
package by.dak.cutting.swing;

import org.jhotdraw.draw.Constrainer;
import org.jhotdraw.draw.DrawingView;
import org.jhotdraw.draw.Figure;
import org.jhotdraw.draw.event.TransformEdit;
import org.jhotdraw.draw.tool.AbstractTool;
import org.jhotdraw.draw.tool.DragTracker;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Collection;
import java.util.LinkedList;

/**
 * <code>DefaultDragTracker</code> implements interactions with the content area of a
 * <code>Figure</code>.
 * <p/>
 * The <code>DefaultDragTracker</code> handles one of the three states of the
 * <code>SelectionTool</code>. It comes into action, when the user presses
 * the mouse button over the content area of a <code>Figure</code>.
 * <p/>
 * Design pattern:<br>
 * Name: Chain of Responsibility.<br>
 * Role: Handler.<br>
 * Partners: {@link org.jhotdraw.draw.tool.SelectionTool} as Handler, {@link org.jhotdraw.draw.tool.SelectAreaTracker} as
 * Handler, {@link org.jhotdraw.draw.tool.HandleTracker} as Handler.
 * <p/>
 * Design pattern:<br>
 * Name: State.<br>
 * Role: State.<br>
 * Partners: {@link org.jhotdraw.draw.tool.SelectAreaTracker} as State, {@link org.jhotdraw.draw.tool.SelectionTool} as
 * Context, {@link org.jhotdraw.draw.tool.HandleTracker} as State.
 *
 * @author Werner Randelshofer
 * @version $Id: DefaultDragTracker.java -1   $
 * @see org.jhotdraw.draw.tool.SelectionTool
 */
public class CuttingDragTracker extends AbstractTool implements DragTracker
{

    private ElementDragHandle elementDragHandle;

    protected Figure anchorFigure;
    /**
     * The drag rectangle encompasses the bounds of all dragged figures.
     */
    protected Rectangle2D.Double dragRect;
    /**
     * The previousOrigin holds the origin of all dragged figures of the
     * previous mouseDragged event. This coordinate is constrained using
     * the Constrainer of the DrawingView.
     */
    protected Point2D.Double previousOrigin;
    /**
     * The anchorOrigin holds the origin of all dragged figures of the
     * mousePressed event.
     */
    protected Point2D.Double anchorOrigin;
    /**
     * The previousPoint holds the location of the mouse of the previous
     * mouseDragged event. This coordinate is not constrained using
     * the Constrainer of the DrawingView.
     */
    protected Point2D.Double previousPoint;
    /**
     * The anchorPoint holds the location of the mouse of the
     * mousePressed event. This coordinate is not constrained using
     * the Constrainer of the DrawingView.
     */
    protected Point2D.Double anchorPoint;
    private boolean isDragging;

    /**
     * Creates a new instance.
     */
    public CuttingDragTracker(Figure figure)
    {
        anchorFigure = figure;
    }

    public CuttingDragTracker()
    {
    }

    @Override
    public void mouseMoved(MouseEvent evt)
    {
        updateCursor(editor.findView((Container) evt.getSource()), evt.getPoint());
    }

    @Override
    public void mousePressed(MouseEvent evt)
    {
        super.mousePressed(evt);
        DrawingView view = getView();

        if (evt.isShiftDown())
        {
            view.setHandleDetailLevel(0);
            view.toggleSelection(anchorFigure);
            if (!view.isFigureSelected(anchorFigure))
            {
                anchorFigure = null;
            }
        }
        else if (!view.isFigureSelected(anchorFigure))
        {
            view.setHandleDetailLevel(0);
            view.clearSelection();
            view.addToSelection(anchorFigure);
        }

        if (!view.getSelectedFigures().isEmpty())
        {

            dragRect = null;
            for (Figure f : view.getSelectedFigures())
            {
                if (dragRect == null)
                {
                    dragRect = f.getBounds();
                }
                else
                {
                    dragRect.add(f.getBounds());
                }
            }


            anchorPoint = previousPoint = view.viewToDrawing(anchor);
            anchorOrigin = previousOrigin = new Point2D.Double(dragRect.x, dragRect.y);
        }
    }

    @Override
    public void mouseDragged(MouseEvent evt)
    {
        DrawingView view = getView();
        if (!view.getSelectedFigures().isEmpty())
        {
            if (isDragging == false)
            {
                isDragging = true;
                updateCursor(editor.findView((Container) evt.getSource()), new Point(evt.getX(), evt.getY()));
            }

            Point2D.Double currentPoint = view.viewToDrawing(new Point(evt.getX(), evt.getY()));

            dragRect.x += currentPoint.x - previousPoint.x;
            dragRect.y += currentPoint.y - previousPoint.y;
            Rectangle2D.Double constrainedRect = (Rectangle2D.Double) dragRect.clone();
            if (view.getConstrainer() != null)
            {
                view.getConstrainer().constrainRectangle(
                        constrainedRect);
            }

            AffineTransform tx = new AffineTransform();
            tx.translate(
                    constrainedRect.x - previousOrigin.x,
                    constrainedRect.y - previousOrigin.y);
            Constrainer c = view.getConstrainer();
            for (Figure f : view.getSelectedFigures())
            {
                f.willChange();
                trackStart(f);
                f.transform(tx);
                trackStep();
                f.changed();
            }

            previousPoint = currentPoint;
            previousOrigin = new Point2D.Double(constrainedRect.x, constrainedRect.y);
        }
    }


    @Override
    public void mouseReleased(MouseEvent evt)
    {
        super.mouseReleased(evt);
        DrawingView view = getView();
        if (!view.getSelectedFigures().isEmpty())
        {
            isDragging = false;
            int x = evt.getX();
            int y = evt.getY();
            updateCursor(editor.findView((Container) evt.getSource()), new Point(x, y));
            Point2D.Double newPoint = view.viewToDrawing(new Point(x, y));

            Collection<Figure> draggedFigures = new LinkedList<Figure>(view.getSelectedFigures());
            Figure dropTarget = getDrawing().findFigureExcept(newPoint, draggedFigures);
            if (dropTarget != null)
            {
                boolean snapBack = dropTarget.handleDrop(newPoint, draggedFigures, view);
                if (snapBack)
                {
                    AffineTransform tx = new AffineTransform();
                    tx.translate(
                            anchorOrigin.x - previousOrigin.x,
                            anchorOrigin.y - previousOrigin.y);
                    Constrainer c = view.getConstrainer();
                    for (Figure f : draggedFigures)
                    {
                        f.willChange();
                        f.transform(tx);
                        trackEnd(snapBack);
                        f.changed();
                    }
                    Rectangle r = new Rectangle(anchor.x, anchor.y, 0, 0);
                    r.add(evt.getX(), evt.getY());
                    maybeFireBoundsInvalidated(r);
                    fireToolDone();
                    return;
                }
            }

            AffineTransform tx = new AffineTransform();
            tx.translate(
                    -anchorOrigin.x + previousOrigin.x,
                    -anchorOrigin.y + previousOrigin.y);
            if (!tx.isIdentity())
            {
                getDrawing().fireUndoableEditHappened(new TransformEdit(
                        draggedFigures, tx));
            }
            trackEnd(false);
        }
        Rectangle r = new Rectangle(anchor.x, anchor.y, 0, 0);
        r.add(evt.getX(), evt.getY());
        maybeFireBoundsInvalidated(r);
        fireToolDone();
    }

    private void trackStart(Figure f)
    {
        if (f instanceof ElementFigure)
        {
            if (elementDragHandle == null)
            {
                elementDragHandle = new ElementDragHandle((ElementFigure) f);
                //это код нужен для того чтобы когда брасаем в пустое место Element попадал в Buffer
                if(editor instanceof CuttingDrawingEditor)
                {
                    elementDragHandle.setFigureClipboard(((CuttingDrawingEditor)editor).getFigureClipboard());
                }
                elementDragHandle.setView(getView());
            }
            elementDragHandle.trackStart();
        }
    }


    private void trackEnd(boolean snapBack)
    {
        if (elementDragHandle != null)
        {
            elementDragHandle.trackEnd(snapBack);
            elementDragHandle = null;
        }
    }

    private void trackStep()
    {
        if (elementDragHandle != null)
        {
            elementDragHandle.trackStep();
        }

//        List<Segment> segments = graySegment.getItems();
//        for (Segment segment : segments)
//        {
//
//        }


    }


    @Override
    public void setDraggedFigure(Figure f)
    {
        anchorFigure = f;
    }
}
