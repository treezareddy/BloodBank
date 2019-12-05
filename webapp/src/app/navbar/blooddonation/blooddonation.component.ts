import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl, FormArray, MinLengthValidator } from '@angular/forms';


import { Router } from '@angular/router';
import { UserServiceService } from 'src/app/services/user-service.service';
import { IUser } from 'src/app/site/registration/iuser';
import { BloodBankService } from 'src/app/services/blood-bank.service';


@Component({
  selector: 'app-blooddonation',
  templateUrl: './blooddonation.component.html',
  styleUrls: ['./blooddonation.component.css']
})
export class BlooddonationComponent implements OnInit {
  Blooddonationform: FormGroup;
  Bloodgroup: any = ['A+','A-','B+','B-','AB+','AB-','O+','O-']
  userList : IUser;
  pin:number;
  Area:any;
 Pincode:any;
 State:any;
  // State: any = ['Andhra Pradesh','Telengana']
  // Area: any = ['VIzag','Vijayawada','Hyderabad']
  constructor(private formBuilder:FormBuilder,private userService:UserServiceService,private router :Router,private bloodBankService : BloodBankService) { }

  ngOnInit() {
  //   this.Blooddonationform = this.formBuilder.group({
  //     username:['',[Validators.required,Validators.pattern('^[a-zA-Z]+$')]],
  //     contactNo:['',[Validators.required,Validators.pattern('^[0-9]+$')]],
  //     state:['',[Validators.required,Validators.pattern('^[a-zA-Z]+$')]],
  //     area:['',[Validators.required,Validators.pattern('^[a-zA-Z]+$')]],
  //     pincode:['',[Validators.required,Validators.pattern('^[0-9]+$')]],
  //     bloodgroup:['',[Validators.required,Validators.pattern('^[a-zA-Z]+$')]]
  // })


  this.Blooddonationform = this.formBuilder.group({
    username:['',[Validators.required]],
    contactNo:['',[Validators.required]],
    state:['',[Validators.required]],
    area:['',[Validators.required]],
    pincode:['',[Validators.required,]],
    bloodgroup:['',[Validators.required,]]
})
  // this.Blooddonationform=new FormGroup({
  //   'username':new FormControl(),
  //   'contactNo': new FormControl(),
  //   'state':new FormControl(),
  //   'area':new FormControl(),
  //   'pincode':new FormControl(),
  //  'bloodgroup':new FormControl()
  // })
  
      
     
      this.bloodBankService.getState().subscribe(
        (data :any) =>
        {
          console.log("hdgv****states---"+data);
          this.State=data;
        }
      )

      // this.bloodBankService.getHospitalList().subscribe(
      //   (data) => {

      //   }
      // );
  }
  get username() {
    return this.Blooddonationform.get('username');
  }
    get contactNo() {
      return this.Blooddonationform.get('contactNo');
    }
  
  get state() {
    return this.Blooddonationform.get('state');
  }

get area() {
  return this.Blooddonationform.get('area');
}
get pincode() {
  return this.Blooddonationform.get('pincode');
}
get bloodgroup() {
  return this.Blooddonationform.get('bloodgroup');
}

selectState($event) {
  console.log("****clicking*****"+event)
  this.bloodBankService.getArea($event.target.value).subscribe(
      (data) => {

        console.log("***Area***"+data);
        this.Area = data;
      }
  )
}

selectArea($event) {
  this.bloodBankService.getPincode($event.target.value).subscribe(
      (data : any) => {
        console.log("***Pincode***"+data);
        this.Pincode = data;
      }
  )
}
onClickDonate(value:any) {

  this.pin = value.pincode;
  console.log("onclickdonate--"+this.pin);
  this.router.navigate(['bookslot/',this.pin]);
}
}
