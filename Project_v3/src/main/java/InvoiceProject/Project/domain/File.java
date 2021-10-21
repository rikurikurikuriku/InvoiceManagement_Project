package InvoiceProject.Project.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class File {
	
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private long id;
		private String fileName, mimeType;

		@Lob
		private byte[] file;
		
		public File() {}
		
		public File(String fileName, String mimeType, byte[] file) {
			this.fileName = fileName;
			this.mimeType = mimeType;
			this.file = file;
		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getFileName() {
			return fileName;
		}

		public void setFileName(String fileName) {
			this.fileName = fileName;
		}

		public String getMimeType() {
			return mimeType;
		}

		public void setMimeType(String mimeType) {
			this.mimeType = mimeType;
		}

		public byte[] getFile() {
			return file;
		}

		public void setFile(byte[] file) {
			this.file = file;
		}


	}


