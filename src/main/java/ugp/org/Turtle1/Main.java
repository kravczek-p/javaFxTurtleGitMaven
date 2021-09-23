

package ugp.org.Turtle1;

        import javafx.stage.Stage;

public class Main extends ugp.org.Turtle1.ShowTurtle
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    protected void Program(Turtle t, Stage s)
    {
        //Write your turtle commands here!

        //For example, this will draw "HI!"
        Actions.DrawH(t, 100);
        t.penUp();
//        t.Right(90);
//        t.Forward(110);
//        t.Right(90);
//        t.penDown();
//        Actions.DrawI(t, 100);
//        t.Left(90);
//        t.penUp();
//        t.Forward(80);
//        t.penDown();
//        t.Left(90);
//        Actions.DrawExclMark(t, 100);
    }
}
//About
//        JavaFx "Imagine" like turtle.
//
//        Topics
//        java javafx turtle-graphics
//        Resources
//        Readme
//        Releases 2
//        Turtle 1.0.5
//        Latest
//        on 21 Jun 2020
//        + 1 release
//        Packages
//        No packages published
