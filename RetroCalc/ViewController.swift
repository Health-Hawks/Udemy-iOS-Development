//
//  ViewController.swift
//  RetroCalc
//
//  Created by Tabor Scott on 9/22/17.
//  Copyright © 2017 Tabor Scott. All rights reserved.
//

import UIKit
import AVFoundation
class ViewController: UIViewController {
    
    @IBOutlet weak var OutputLbl: UILabel!
    var btnSound: AVAudioPlayer!
    enum Operation: String{
        case Divide = "/"
        case Multiply = "*"
        case Subtract = "-"
        case Add = "+"
        case Empty = "Empty"
    }
    var currentOperation = Operation.Empty
    var runningNumber = ""
    var leftValStr = ""
    var rightValStr = ""
    var result = ""
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        let path = Bundle.main.path(forResource: "btn", ofType: "wav")
        let soundURL = URL(fileURLWithPath: path!)
        
        do{
            try btnSound = AVAudioPlayer(contentsOf: soundURL)
            btnSound.prepareToPlay()
        }
        catch let err as NSError{
            print(err.debugDescription)
        }
        OutputLbl.text = "0"
    }
    @IBAction func numberPressed(sender: UIButton){
        playSound()
        
        runningNumber += "\(sender.tag)"
        OutputLbl.text = runningNumber
    }
    @IBAction func onDividePressed (sender: AnyObject){
        processOperation(operation: .Divide)
    }
    @IBAction func onMultiplyPressed (sender: AnyObject){
        processOperation(operation: .Multiply)
    }
    @IBAction func onSubtractPressed (sender: AnyObject){
        processOperation(operation: .Subtract)
    }
    @IBAction func onAddPressed (sender: AnyObject){
        processOperation(operation: .Add)
    }
    @IBAction func onEqualPressed(sender: AnyObject){
        processOperation(operation: currentOperation)
    }
    func playSound(){
        if btnSound.isPlaying{
            btnSound.stop()
        }
        btnSound.play()
    }
    func processOperation(operation: Operation){
        if currentOperation != Operation.Empty{
            if runningNumber != ""{
                rightValStr = runningNumber
            }
            if currentOperation == Operation.Multiply{
                result = "\(Double(leftValStr)! * Double(rightValStr)!)"
            }
            else if currentOperation == Operation.Divide{
                result = "\(Double(leftValStr)! / Double(rightValStr)!)"
            }
            else if currentOperation == Operation.Subtract{
                result = "\(Double(leftValStr)! - Double(rightValStr)!)"
            }
            else if currentOperation == Operation.Add{
                result = "\(Double(leftValStr)! + Double(rightValStr)!)"
            }
            
            leftValStr = result
            OutputLbl.text = result
            currentOperation = operation
        }
        else {
            leftValStr = runningNumber
            runningNumber = ""
            currentOperation = operation
        }
    }


}

