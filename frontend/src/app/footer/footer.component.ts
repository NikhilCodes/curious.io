import { Component } from '@angular/core';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent {
  year: string = Date().split(' ')[3];
  email = 'nikhil.nixel@gmail.com';
  githubUserName = 'NikhilCodes';
  fullName = 'Nikhil Nayak';
}
