import { Component, forwardRef, Input, OnInit } from '@angular/core';
import { ControlValueAccessor, NG_VALUE_ACCESSOR } from '@angular/forms';

@Component({
  selector: 'app-inputupdate',
  templateUrl: './inputupdate.component.html',
  styleUrls: ['./inputupdate.component.scss'],
  providers: [{
    provide: NG_VALUE_ACCESSOR,
    useExisting: forwardRef(() => InputupdateComponent),
    multi: true
  }]
})
export class InputupdateComponent implements OnInit, ControlValueAccessor {

  @Input() id!: string;
  @Input() label!: string;
  @Input() value!: string;
  @Input() placeholder!: string;

  allow: boolean = false;
  touchedFormControl!: () => void;
  setFormControl!: (value: string) => void;

  ngOnInit(): void {
  }

  setValue() {
    this.touchedFormControl();
    this.setFormControl(this.value);
  }

  writeValue(obj: any): void {
    this.value = obj;
  }

  registerOnChange(fn: any): void {
    this.setFormControl = fn;
  }

  registerOnTouched(fn: any): void {
    this.touchedFormControl = fn;
  }

  setDisabledState(isDisabled: boolean): void {
    this.allow = isDisabled;
  }

}
